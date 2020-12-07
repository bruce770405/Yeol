import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { BottomNavigation, BottomNavigationTab, Icon, Layout, Text } from '@ui-kitten/components';
import { HomeScreen } from '../home/Home';
import { SettingScreen } from '../setting/Setting';

const { Navigator, Screen } = createBottomTabNavigator();

const BrowserIcon = (props) => (
    <Icon name='browser-outline' {...props} />
);
const SettingIcon = (props) => (
    <Icon name='options-outline' {...props} />
);

const BottomTabBar = ({ navigation, state }) => (
    <BottomNavigation
        selectedIndex={state.index}
        onSelect={index => navigation.navigate(state.routeNames[index])}>
        <BottomNavigationTab icon={BrowserIcon} title='Articles' />
        <BottomNavigationTab icon={SettingIcon} title='Setting' />
    </BottomNavigation>
);

const TabNavigator = () => (
    <Navigator tabBar={props => <BottomTabBar {...props} />}>
        <Screen name='Home' component={HomeScreen} />
        <Screen name='Details' component={SettingScreen} />
    </Navigator>
);

export const AppNavigator = () => (
    <NavigationContainer>
        <TabNavigator />
    </NavigationContainer>
);