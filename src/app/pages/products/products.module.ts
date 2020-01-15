export class Products {
  categoryUID: string;
  title: string;
  subTitle: string;
  content: string;
  unitCost: number;
  unitPrice: number;
  gender: number;
  data: any;
  images: [
    {
      imageUID: string,
      imageUrl: string
    }
  ] = [
      {
        imageUID: '',
        imageUrl: ''
      }
    ];
}
