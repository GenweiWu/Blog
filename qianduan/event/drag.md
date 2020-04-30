```html
<div class="dropzone">
  <div id="draggable" draggable="true" ondragstart="event.dataTransfer.setData('text/plain',null)">
    This div is draggable
  </div>
</div>
<div class="dropzone"></div>
<div class="dropzone"></div>
<div class="dropzone"></div>
```

```css
  #draggable {
    width: 200px;
    height: 20px;
    text-align: center;
    background: white;
  }

  .dropzone {
    width: 200px;
    height: 20px;
    background: blueviolet;
    margin-bottom: 10px;
    padding: 10px;
  }
```

```js
var dragged;

/* 拖动目标元素时触发drag事件 */
document.addEventListener("drag", function (event) {

}, false);

//开始拖动：event对应被拖动的元素
document.addEventListener("dragstart", function (event) {
  // 保存拖动元素的引用(ref.)
  dragged = event.target;
  // 使其半透明
  event.target.style.opacity = .5;
}, false);

//结束拖动：event对应被拖动的元素
document.addEventListener("dragend", function (event) {
  // 重置透明度
  event.target.style.opacity = "";
}, false);

//用来启动drop，就是移上去显示可drop，默认是阻止的标记
/* 放置目标元素时触发事件 */
document.addEventListener("dragover", function (event) {
  // 阻止默认动作以启用drop
  event.preventDefault();
}, false);

//拖动进入，event对应拖动进入的元素
document.addEventListener("dragenter", function (event) {
  // 当可拖动的元素进入可放置的目标时高亮目标节点
  console.log("dragenter:" + event.target.id)
  if (event.target.className == "dropzone") {
    event.target.style.background = "purple";
  }

}, false);

//拖动离开，，event对应拖动离开的元素
document.addEventListener("dragleave", function (event) {
  // 当拖动元素离开可放置目标节点，重置其背景
  console.log("dragleave:" + event.target.id)
  if (event.target.className == "dropzone") {
    event.target.style.background = "";
  }

}, false);

//结束拖动，释放鼠标，event对应释放的时候所在的元素
document.addEventListener("drop", function (event) {
  console.log("drop:" + event.target.id)
  // 阻止默认动作（如打开一些元素的链接）
  event.preventDefault();
  // 将拖动的元素到所选择的放置目标节点中
  if (event.target.className == "dropzone") {
    event.target.style.background = "";
    dragged.parentNode.removeChild(dragged);
    event.target.appendChild(dragged);
  }

}, false);
```

### 参考
- https://jsitor.com/-jNkEd_P0#
- https://developer.mozilla.org/zh-CN/docs/Web/API/Document/drag_event

