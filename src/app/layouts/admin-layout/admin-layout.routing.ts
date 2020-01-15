import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { ProductsComponent } from 'src/app/pages/products/products.component';
import { CategoryComponent } from 'src/app/pages/category/category.component';
import { CreateCategoryComponent } from '../../pages/category/create-category/create-category.component';
import { ProductCreateComponent } from 'src/app/pages/products/product-create/product-create.component';
import { CategoryUpdateComponent } from 'src/app/pages/category/category-update/category-update.component';
import { ProductUpdateComponent } from 'src/app/pages/products/product-update/product-update.component';
import { InvoiceComponent } from 'src/app/pages/invoice/invoice.component';
import { InvoiceUpdateComponent } from 'src/app/pages/invoice/invoice-update/invoice-update.component';
import { InvoiceCreateComponent } from 'src/app/pages/invoice/invoice-create/invoice-create.component';

export const AdminLayoutRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  // { path: 'user', component: UserComponent },
  // { path: 'table', component: TableComponent },
  { path: 'product', component: ProductsComponent },
  { path: 'product/create', component: ProductCreateComponent },
  { path: 'product/edit/:id', component: ProductUpdateComponent },
  { path: 'category', component: CategoryComponent },
  { path: 'category/create', component: CreateCategoryComponent },
  { path: 'category/edit/:id', component: CategoryUpdateComponent },
  { path: 'invoice', component: InvoiceComponent },
  { path: 'invoice/create', component: InvoiceCreateComponent },
  { path: 'invoice/edit/:id', component: InvoiceUpdateComponent },
];
