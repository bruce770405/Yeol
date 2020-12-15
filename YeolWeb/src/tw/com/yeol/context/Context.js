import React, { useReducer } from 'react';
import { memberReducer, memberInitialState } from './Reducer';

const AuthorizedStore = React.createContext();
const AuthorizedDispatchContext = React.createContext();

// const MemberStore = React.createContext();

/**
 * for export.
 */
export function useAuthorizedState() {
    const context = React.useContext(AuthorizedStore);
    if (context === undefined) {
        throw new Error("useAuthState must be used within a AuthProvider");
    }
    return context;
}

export function useAuthorizedDispatch() {
    const context = React.useContext(AuthorizedDispatchContext);
    if (context === undefined) {
        throw new Error("useAuthDispatch must be used within a AuthProvider");
    }
    return context;
}

export const AuthorizedProvider = ({ children }) => {
    const [member, dispatch] = useReducer(memberReducer, memberInitialState);

    return (
        <AuthorizedStore.Provider value={member}>
            <AuthorizedDispatchContext.Provider value={dispatch}>
                {children}
            </AuthorizedDispatchContext.Provider>
        </AuthorizedStore.Provider>
    );
};