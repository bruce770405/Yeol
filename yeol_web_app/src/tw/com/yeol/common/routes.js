import Home from '../../../../pagecode/home/Home';
import Login from '../../../../pagecode/login/Login';

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
  }
];

export default routes;