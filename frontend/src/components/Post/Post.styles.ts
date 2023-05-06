import { makeStyles } from "tss-react/mui";

export const useStyles = makeStyles()((theme) => ({
  wrapper: {
    padding: 16,
    background: "#fff",
    boxShadow: "0px 5px 21px 0px rgba(34, 60, 80, 0.2)",
    marginBottom: 16,
    position: "relative",
  },

  from: {
    color: "orange",
    position: "absolute",
    top: 16,
    right: 16,
  },

  title: {
    fontSize: 24,
    fontWeight: 600,
  },

  text: {
    fontSize: 12,
    marginTop: 8,
  },
}));
