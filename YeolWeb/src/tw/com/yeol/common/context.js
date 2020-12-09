
const ContextStore = React.createContext({
    datas: []
})

function articlesReducer(state, action) {
    switch (action.type) {
        case 'ADD_PRODUCT':
            return Object.assign({}, state, {
                products: state.products.concat({ id: state.products.length })
            })
        default:
            return state
    }
}