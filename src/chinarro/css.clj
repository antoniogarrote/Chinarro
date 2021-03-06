(ns chinarro.css)

(defn pocho-css []
"* {
  margin: 0px;
  padding: 0px;
}
body {
  font-family: Futura, Helvetica;
  background: #222;
}
h1 {
  text-shadow: 0px 0px 15px magenta;
  color: magenta;
}
a {
  text-decoration: none;
  padding: 2px 5px 1px 5px;
}

/* Layout */
#main {
  width: 70%;
  margin: 0px auto;
}
#head {
  letter-spacing: 5px;
  text-align: right;
  margin-bottom: 20px;
  font-size: 2.5em;
}
#head a {
  padding: 0px;
}
#footer {
 color: white;
 font-size: 1em;
 text-align: center;
 margin-top: 40px;
}
#footer a {
  background: transparent;
  color: white;
 text-shadow: 0px 0px 5px white;
}
#footer a:hover {
  background: transparent;
  color: white;
  text-shadow: 0px 0px 10px white;
}


/* Message */
div.message {
  padding: 20px 10px 20px 10px;
  margin-bottom: 0px;
  letter-spacing: 1px;
  color: white;
  font-size: 1.5em;
}
div.message a {
  background: transparent;
  color: cyan;
}
div.message a:hover {
  background: yellow;
  color: black;
}

/* Tags */
#tags {
  margin-top: 60px;
  text-align: center;
}
div.tag {
  display: inline-table;
  font-size: 1.5em;
  margin-bottom: 10px;
}
div.tag a {
  background: cyan;
  color: #000;
}
div.tag a:hover {
  background: yellow;
  color: #000;
}

")
