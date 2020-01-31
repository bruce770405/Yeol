import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import { red } from '@material-ui/core/colors';
import Typography from '@material-ui/core/Typography';
import { CardComponent } from '../../../component/CardComponent';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import Divider from '@material-ui/core/Divider';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import MailIcon from '@material-ui/icons/Mail';
import Badge from '@material-ui/icons/Mail';


/**
 * 畫面卡片式元件
 * TODO
 * @author BruceHsu
 * @version
 * @since
 * @param userName 
 * @param title  
 * @param createTime
 * @param watchCount
 */
export const ArticleList = (props) => {

  const classes = useStyles();

  return (

    <CardComponent>
      <List className={classes.root}>
        {
          props.data ?
            Object.values(props.data).map(item =>
              <React.Fragment>
                <ListItem alignItems="flex-start">
                  <ListItemAvatar>
                    <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
                  </ListItemAvatar>
                  <ListItemText
                    primary={item.title}
                    secondary={
                      <React.Fragment>
                        <Typography
                          component="span"
                          variant="body2"
                          className={classes.inline}
                          color="textPrimary"
                        >
                          Ali Connors
                        </Typography>
                        {item.constant}
                      </React.Fragment>
                    }
                  />
                  <Badge badgeContent={4} color="secondary">
                    <MailIcon />
                  </Badge>
                  <Badge badgeContent={4} color="secondary">
                    <MailIcon />
                  </Badge>
                </ListItem>
                <Divider variant="inset" component="li" />
              </React.Fragment>) : null
        }
      </List>
    </CardComponent >

  );
}




const useStyles = makeStyles(theme => ({
  name: {
    paddingLeft: 3
  },
  media: {
    height: 0,
    paddingTop: '56.25%', // 16:9
  },
  avatar: {
    backgroundColor: red[500],
    width: 28,
    height: 28,
  },
  filePic: {
    color: red[500],
  },
}));