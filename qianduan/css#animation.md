
```html
  <section>
    <div class="main"></div>
  </section>
```

```css
.main{
  background-color: #1766aa;
    margin: 20px;
    border: 5px solid #333;
    width: 150px;
    height: 150px;
    border-radius: 50%;
}

.main{
  /* animation: 3s ease-in 1s infinite reverse both running slidein; */
    animation-name: slidein;
    animation-duration: 3s;
    animation-timing-function: ease-in;
    animation-delay: 0.1s;
    animation-iteration-count: infinite;
    animation-direction: normal;
    animation-fill-mode: none;
    animation-play-state: running;
}

@keyframes slidein {
 0% {margin-left: 0%;}
 100% {margin-left: 80%;}
}
```

![animation](https://user-images.githubusercontent.com/16630659/59418639-f21ff880-8dfb-11e9-8771-3fccb3da8088.gif)



