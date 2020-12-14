import React, { useEffect, useState } from 'react';
import { makeStyles } from "@material-ui/core/styles";
import AssignmentTurnedInIcon from '@material-ui/icons/AssignmentTurnedIn';
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import Checkbox from '@material-ui/core/Checkbox';
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";

function App() {

  // Conjunto de Tasks, proveniente do banco de dados e atualizado conforme uso
  const [data, setData] = useState<any[]>([]);

  // Valores dos campos do formulário
  const [title, setTitle] = React.useState("");
  const [description, setDescription] = React.useState("");
  const [status, setStatus] = React.useState(false);
  const [creation, setCreation] = React.useState(new Date());
  const [lastEdition, setLastEdition] = React.useState(new Date());
  const [removal, setRemoval] = React.useState(new Date());
  const [conclusion, setConclusion] = React.useState(new Date());

  // Handlers de eventos de atualização do formulário 
  const handleTitleChange = (event: { target: { value: React.SetStateAction<string>; }; }) => setTitle(event.target.value);

  // Inicialização, com GET para listar as Tasks
  useEffect(() => {
    fetch("/tasklist")
      .then((response) => response.json())
      .then((json) => {
        setData([...json]);
      });
  }, []);

  // Estilização
  const useStyles = makeStyles(theme => ({
    head: {
      marginTop: theme.spacing(7),
      display: "flex",
      flexDirection: "column",
      width: "100%",
      alignItems: "center"
    },
    avatar: {
      margin: theme.spacing(1),
      backgroundColor: theme.palette.primary.main
    },
    form: {
      width: "100%",
      marginTop: theme.spacing(2)
    },
    submit: {
      margin: theme.spacing(1, 0, 2),
      width: "15%"
    },
    textField: {
      marginLeft: theme.spacing(1),
      marginRight: theme.spacing(1),
      width: "80%"
    }
  }));
  const classes = useStyles();

  // Handler do botão Save
  const handleSubmit = (values: any) => {
    const toSave = { title, description, status, creation, lastEdition, removal, conclusion };

    asyncSave(toSave);

    const result: any[] = [...data, toSave];

    setData(result);
  }

  async function asyncSave(toSave: any) {
    await fetch('/savetask', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(toSave)
    });
  }

  // Handler da checkbox que indica se Task está ou não completa
  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    // TODO
  };

  // Componentes da interface
  return (
    <Container component="main" maxWidth="sm">
      <CssBaseline />

      <div>
        <div className={classes.head}>
          <Avatar className={classes.avatar} >
            <AssignmentTurnedInIcon />
          </Avatar>
        </div>
        <Typography component="h1" variant="h5" align="center">
          My Beautiful Task List!
      </Typography>


        {data.map(item => (
          <Grid>
            <Checkbox checked={item.status} onChange={handleStatusChange} />
            {item.title}
          </Grid>
        ))}

        <form className={classes.form} noValidate>
          <Grid>
            <TextField
              name="title"
              className={classes.textField}
              required
              label="Title"
              value={title}
              onChange={handleTitleChange}
            />

            <Button
              variant="contained"
              color="primary"
              //preventDefault
              className={classes.submit}
              onClick={handleSubmit}
            >
              Save
            </Button>
          </Grid>
        </form>

      </div>

    </Container>
  );
}

export default App;