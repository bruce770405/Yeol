// 建立 reducer
function memberReducer(state, action) {
    console.log('done')
    switch (action.type) {
        case 'LOGIN':
            state.member = action.payload.data;
            return {
                ...state,
                isAuthenticated: true,
                member: action.payload.user,
                token: action.payload.data
            };
        case 'LOGOUT':
            localStorage.clear();
            return {
                ...state,
                isAuthenticated: false,
                user: null
            };
        default:
            return state
    }
}

// 建立 member initial state
const memberInitialState = {
    token: undefined,
    user: null
}

export {
    memberReducer,
    memberInitialState
}