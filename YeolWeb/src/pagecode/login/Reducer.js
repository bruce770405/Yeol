// 建立 reducer
function loginReducer(state, action) {
    switch (action.type) {
        case 'LOGIN':
            return Object.assign({}, state, {
                todos: state.todos.concat('eat')
            })
        default:
            return state
    }
}

// 建立 member initial state
const memberInitialState = {
    member: []
}

export {
    loginReducer,
    memberInitialState
}