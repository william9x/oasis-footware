import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  slides = [
    { img: "assets/images/item-07.jpg" },
    { img: "assets/images/item-02.jpg" },
    { img: "assets/images/item-03.jpg" },
    { img: "assets/images/item-05.jpg" }
  ];
  slideConfig = { "slidesToShow": 4, "slidesToScroll": 4 };

  constructor() { }

  ngOnInit() {
  }

  slickInit(e) {
    console.log('slick initialized');
  }

  breakpoint(e) {
    console.log('breakpoint');
  }

  afterChange(e) {
    console.log('afterChange');
  }

  beforeChange(e) {
    console.log('beforeChange');
  }

}
