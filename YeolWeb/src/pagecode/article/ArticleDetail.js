import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import Grid from '@material-ui/core/Grid';

export default function ArticleDetail(props) {
    const classes = useStyles();
    const [expanded, setExpanded] = React.useState(false);

    return (
        <div style={{ 'marginTop': 32 }}>
            <Grid container spacing={6}>
                <Card className={classes.root}>
                    123
                </Card>
            </Grid>
        </div>
    );
}

const useStyles = makeStyles((theme) => ({
    root: {
        maxWidth: '100%',
    }
}));
