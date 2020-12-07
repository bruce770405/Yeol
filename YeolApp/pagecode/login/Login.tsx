import React from 'react';
import { SafeAreaView, StyleSheet, View } from 'react-native';
import { Avatar, Card, Button, ButtonGroup, Icon, List, ListItem, Text, TopNavigation } from '@ui-kitten/components';

const data = new Array(8).fill({
  title: 'Item',
});

const StarIcon = (props) => (
  <Icon {...props} name='star' />
);

const renderItemHeader = (headerProps, info) => (
  <ListItem
    title={info.item.title + info.index}
    description='BruceHsu'
    accessoryLeft={() => <Avatar source={require('../../assets/avatar.png')} />}
  ></ListItem>
);

const renderItemFooter = (footerProps) => (
  <View {...footerProps} style={[footerProps.style, footerProps.footerContainer]}>
    <ButtonGroup status='danger' size='tiny'>
      <Button accessoryLeft={StarIcon} />
      <Button accessoryLeft={StarIcon} />
      <Button accessoryLeft={StarIcon} />
    </ButtonGroup>
  </View>
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
        Hello world
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
  footerContainer: {
    flexDirection: 'row',
    justifyContent: 'flex-end',
  }
});