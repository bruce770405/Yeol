import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Typography from '@material-ui/core/Typography';
import Avatar from '@material-ui/core/Avatar';
import avator from '../../../assets/avatar/batman_hero_avatar_comics.png';
import Divider from '@material-ui/core/Divider';


/**
 * 畫面卡片式元件
 * @author BruceHsu
 * @version
 * @since
 */
export const SubList = (props) => {

  const classes = useStyles();
  // const [checked, setChecked] = React.useState([1]);

  // const handleToggle = (value) => () => {
  //   const currentIndex = checked.indexOf(value);
  //   const newChecked = [...checked];

  //   if (currentIndex === -1) {
  //     newChecked.push(value);
  //   } else {
  //     newChecked.splice(currentIndex, 1);
  //   }

  //   setChecked(newChecked);
  // };

  return (
    <Paper elevation={0} className={classes.sidebarAboutBox}>
      <Typography variant="h6" component="h6">
        社群活躍排行
      </Typography>
      <Divider />
      <List dense className={classes.root}>
        {[0, 1, 2, 3].map((value) => {
          const labelId = `checkbox-list-secondary-label-${value}`;
          return (
            <React.Fragment>
              <ListItem key={value} button>
                <ListItemAvatar>
                  <Avatar
                    alt={`Avatar n°${value + 1}`}
                    src={avator}
                  />
                </ListItemAvatar>
                <ListItemText id={labelId} primary={`member name ${value + 1}`} />
                {/* <ListItemSecondaryAction>
                <Checkbox
                  edge="end"
                  onChange={handleToggle(value)}
                  checked={checked.indexOf(value) !== -1}
                  inputProps={{ 'aria-labelledby': labelId }}
                />
              </ListItemSecondaryAction> */}
              </ListItem>
              <Divider variant="middle"/>
            </React.Fragment>
          );
        })}
      </List>
    </Paper>
  );
}

const useStyles = makeStyles((theme) => ({
  sidebarAboutBox: {
    padding: theme.spacing(2),
    backgroundColor: theme.palette.grey[200],
  }
}));