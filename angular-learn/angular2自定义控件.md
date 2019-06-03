angular2 自定义控件(学习笔记)
==

## 一、如何支持ngModel绑定?

https://angular.io/api/forms/ControlValueAccessor

需要实现`ControlValueAccessor`接口，对应四个方法
```ts
//Set touched on blur
    onBlur() {
        this.onTouchedCallback();
    }

    //From ControlValueAccessor interface
    writeValue(value: any) {
        // debugger;
        if (value !== this.innerValue) {
            this.innerValue = value;
        }
    }

    //From ControlValueAccessor interface
    registerOnChange(fn: any) {
        this.onChangeCallback = fn;
    }

    //From ControlValueAccessor interface
    registerOnTouched(fn: any) {
        this.onTouchedCallback = fn;
    }
```
其中的`writeValue`方法，会在你通过ngModel设置值的时候，触发`writeValue`方法。  
入参就是ngModel绑定传递来的值，无论是字符串，还是动态变量的值。

## 二、get和set方法
实现自定义控件的时候,我们会在html绑定一些属性，比如`value1`,`value2`.
```html
template: `<div class="form-group">
				<label><ng-content></ng-content>
					<input [(ngModel)]="value1"  
							class="form-control" 
							(blur)="onBlur()" >
					 <input [(ngModel)]="value2"  
							class="form-control" 
							(blur)="onBlur()" >
				</label>
			</div>`
```

那么对应的get和set方法如下：  

1、你在界面上输入的时候，会触发对应的set方法，界面上内容回显的时候会调用get方法。

```ts
    // get accessor
    get value1(): any {
        return this.innerValue;
    };

    //set accessor including call the onchange callback
    set value1(v: any) {
        if (v !== this.innerValue) {
            this.innerValue = v;
            this.onChangeCallback(v);
        }
    }

    // get accessor
    get value2(): any {
        return this.innerValue;
    };

    //set accessor including call the onchange callback
    set value2(v: any) {
        if (v !== this.innerValue) {
            this.innerValue = v;
            this.onChangeCallback(v);
        }
    }
```

2、发现下面的场景也会触发对应的set方法
```ts
export class CustomCounterComponent {

    counterValue = 0;
    @Output() counter11Change = new EventEmitter();
  
    @Input()
    get counter11() { 
        return this.counterValue;
    }

    set counter11(val) {
        this.counterValue = val;
        this.counter11Change.emit(this.counterValue);
    }

    decrement() {
        this.counter11--;    // <==这里的修改也会触发set方法!!!
    }

    increment() {
        this.counter11++;    // <==这里的修改也会触发set方法!!!
    }
}
```

## 三、ngModel绑定后，在ts中读取的变量是字符串还是个自定义对象，是什么决定的？

当前的理解如下：
1. 继承`ControlValueAccessor`接口时，有一个`registerOnChange`方法。  
**我的理解是，这个接口会被特定的底层方法调用，我们通过这个方法注入一个底层方法，就可以在后面手动调用这个方法，来同步ngModel的改变**
```ts
registerOnChange(fn: any) {
	this.onChangeCallback = fn;
}
```

2. 在后续改动值的时候，手动调用下上面注入的方法。  
比如：
```ts
set value(v: any) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCallback(v);      // <==这里手动调用下，同时这里传入的参数也决定了ngModel的值
      this.dateTimeStr = this.getTitle(this.value);
    }
}
```




