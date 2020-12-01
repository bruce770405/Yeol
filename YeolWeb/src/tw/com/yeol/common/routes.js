import Home from '../../../../pagecode/home/Home';
import Login from '../../../../pagecode/login/Login';
import SignUp from '../../../../pagecode/signup/SignUp';

/**
 * 路由.
 */
const routes = [{
    path: '/',
    component: Home,
    exact: true,
    breadcrumbName: 'Home'
  },
  {
    path: '/login',
    component: Login,
    exact: true,
    breadcrumbName: 'Login'
  },
  {
    path: '/signup',
    component: SignUp,
    exact: true,
    breadcrumbName: 'SignUp'
  }
];

export default routes;