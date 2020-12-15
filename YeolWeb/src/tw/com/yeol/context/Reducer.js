import React, { useReducer } from "react";

let user = localStorage.getItem("currentUser")
    ? JSON.parse(localStorage.getItem("currentUser")).name
    : "";

let token = localStorage.getItem("currentUser")
    ? JSON.parse(localStorage.getItem("currentUser")).token
    : "";

// 建立 reducer
function memberReducer(state, action) {
    switch (action.type) {
        case 'PRE_LOGIN':
            return {
                ...memberInitialState,
                loading: true
            };
        case 'LOGIN':
            return {
                ...memberInitialState,
                user: action.payload.name,
                token: action.payload.token,
                loading: false
            };
        case 'LOGOUT':
            return {
                ...memberInitialState,
                user: '',
                token: '',
                loading: false
            };
        case 'LOGIN_FAIL':
            return {
                ...memberInitialState,
                user: '',
                token: '',
                errorMessage: 'fail login...',
                loading: false
            };
        default:
            throw new Error(`Unhandled action type: ${action.type}`);
    }
}

// 建立 member initial state
const memberInitialState = {
    user: "" || user,
    token: "" || token,
    loading: false,
    errorMessage: null
}

export {
    memberReducer,
    memberInitialState
}