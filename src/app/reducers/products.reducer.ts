import { createAction, props, createReducer, on, Action } from '@ngrx/store';
import { Products } from '../models/product.model';


export interface State {
    listProducts: Products[];
}

export const initialState: State = {
    listProducts: []
};
export const setProducts = createAction('Set list products', props<{ listProducts: Products[] }>());


const productReducer = createReducer(
    initialState,
    on(setProducts, (state, { listProducts }) => ({ ...state, listProducts })),
);

export function reducer(state: State | undefined, action: Action) {
    return productReducer(state, action);
}