export class CustomerInvoice {
    id: number;
    orderName: string;
    orderEmail: string;
    orderAddress: string;
    orderPhone: string;
    discountValue: number;
    productValue: number;
    status: number;
    totalValue: number;
    productIds: string[] = [];
}
