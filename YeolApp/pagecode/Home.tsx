import React from 'react';
import { SafeAreaView, StyleSheet } from 'react-native';
import { Avatar, Card, List, ListItem, Text, TopNavigation } from '@ui-kitten/components';

const data = new Array(8).fill({
    title: 'Item',
});

const renderItemHeader = (headerProps, info) => (
    <ListItem
        title={ info.item.title + info.index}
        description='BruceHsu'
        accessoryLeft={() => <Avatar source={require('../assets/avatar.png')} />}
    //     accessoryRight={() =>
    //         <View {...headerProps}>
    //             <Text category='h6'>
    //                 {info.item.title} {info.index + 1}
    //             </Text>
    //         </View>}
    // />
    ></ListItem>
);

const renderItemFooter = (footerProps) => (
    <Text {...footerProps}>
        
    </Text>
);

export const HomeScreen = ({ navigation }) => {

    const title = (info) => (
        <Card
            style={styles.item}
            status='basic'
            header={headerProps => renderItemHeader(headerProps, info)}
            footer={renderItemFooter}>
            <Text>
                Hello world
          </Text>
        </Card>
    );


    return (
        <SafeAreaView style={{ flex: 1 }}>
            <TopNavigation title={(evaProps) => <Text category='h2'>Yeol</Text>} alignment='center' />
            <List
                style={styles.container}
                contentContainerStyle={styles.contentContainer}
                data={data}
                renderItem={title}
            />
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
        maxHeight: 1024,
    },
    contentContainer: {
        paddingHorizontal: 8,
        paddingVertical: 4,
    },
    item: {
        marginVertical: 4,
    },
});