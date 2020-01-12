import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { ProductListComponent } from './pages/product-list/product-list.component';
import { ProductDetailComponent } from './pages/product-list/product-detail/product-detail.component';
import { AboutComponent } from './pages/about/about.component';
import { ContactComponent } from './pages/contact/contact.component';
import { BlogComponent } from './pages/blog/blog.component';
import { ProductFilterByCategoryComponent } from './pages/product-list/product-filter-by-category/product-filter-by-category.component';


const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'product', component: ProductListComponent },
  { path: 'product/:id', component: ProductDetailComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'product/filterByCategory/:id', component: ProductFilterByCategoryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
