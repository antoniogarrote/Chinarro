# Chinarro The Robot

Chinarro is an implementation of ![Pocho](http://github.com/malditogeek/Pocho) written in Clojure:

1. Chinarro talks XMPP and hangs out in your MUC Jabber room waiting for action.
2. When a \#hashtagged message is emited to the room, Chinarro jumps to scene
and store it for you.
3. The messages can be visited later via web.

## Author
Mauro Pompilio <hackers.are.rockstars@gmail.com>
Antonio Garrote <antoniogarrote@gmail.com>

## License
Do whatever yo want

## How to use it

1. Install Leiningen

  $ wget http://github.com/technomancy/leiningen/raw/stable/bin/lein
  $ ./lein self-install

2. git clone Chinarro

3. Edit configuration

  $ emacs src/chinarro/configuration

4. Compile

  $ lein deps
  $ lein compile
  $ lein uberjar

5. Run Chinarro bot

  $ java -jar chinarro-standalone.jar bot

6. Run Chinarro web

  $ java -jar chinarro-standalone.jar web
