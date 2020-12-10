import React from 'react';
export const ContextStore = React.createContext();

function articlesReducer(state, action) {
    switch (action.type) {
        case 'ADD_ARTICLE':
            return Object.assign({}, state, {
                products: state.products.concat({ id: state.products.length })
            })
        default:
            return state
    }
}