import Home from '../../../../pagecode/home/Home';
import Login from '../../../../pagecode/login/Login';
import SignUp from '../../../../pagecode/signup/SignUp';
import ArticleDetail from '../../../../pagecode/article/ArticleDetail';

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
    path: '/article/detail',
    component: ArticleDetail,
    exact: true,
    breadcrumbName: 'ArticleDetail'
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