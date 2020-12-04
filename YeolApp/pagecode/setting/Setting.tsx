import React from 'react';
import { Button, Icon, List, ListItem, TopNavigation, Text } from '@ui-kitten/components';
import { StyleSheet, SafeAreaView } from 'react-native';


const data = new Array(8).fill({
    title: 'Title for Item',
    description: 'Description for Item',
});

export const SettingScreen = ({ navigation }) => {
    const renderItemAccessory = (props) => (
        <Button size='tiny'>FOLLOW</Button>
    );

    const renderItemIcon = (props) => (
        <Icon {...props} name='person' />
    );

    const renderItem = ({ item, index }) => (
        <ListItem
            title={`${item.title} ${index + 1}`}
            description={`${item.description} ${index + 1}`}
            accessoryLeft={renderItemIcon}
            accessoryRight={renderItemAccessory}
        />
    );

    return (
        <SafeAreaView style={{ flex: 1 }}>
            <TopNavigation title={(evaProps) => <Text category='h2'>Yeol</Text>} alignment='center' />

            <List
                style={styles.container}
                data={data}
                renderItem={renderItem}
            />
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        maxHeight: 1024,
    },
});