
> https://stackoverflow.com/a/36614339
```
@Component({
  ...
  directives: [SomeComponent],
  template: `
  <div><span #myVar>xxx</span><div>
  <some-comp></some-comp>`
})
class MyComponent {
  @ViewChild('myVar') myVar:ElementRef;
  @ViewChild(SomeComponent) someComponent:SomeComponent;
  
  ngAfterViewInit() {
    console.log(this.myVar.nativeElement.innerHTML);
    console.log(this.someComponent);
  }
}
```
