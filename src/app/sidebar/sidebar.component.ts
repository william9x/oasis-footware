import { Component, OnInit } from '@angular/core';

export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/dashboard',     title: 'Dashboard',         icon:'nc-bank',       class: '' },
  { path: '/user',          title: 'User Profile',      icon:'nc-single-02',  class: '' },
  // { path: '/table',         title: 'Table',        icon:'nc-tile-56',    class: '' },
  { path: '/product',          title: 'Product',      icon:'nc-shop',  class: '' },
  { path: '/category',          title: 'Category',      icon:'nc-bullet-list-67',  class: '' },
  { path: '/report',          title: 'Report',      icon:'nc-paper',  class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

}
