# Chinarro

Chinarro is an implementation of [Pocho](http://github.com/malditogeek/Pocho) written in Clojure:

1. Chinarro talks XMPP and hangs out in your MUC Jabber room waiting for action.
2. When a \#hashtagged message is emited to the room, Chinarro jumps to scene
and store it for you.
3. The messages can be visited later via web.

## Author
Antonio Garrote <antoniogarrote@gmail.com>
Mauro Pompilio <hackers.are.rockstars@gmail.com>

## License
Do whatever yo want

## How to use it

 * Install Leiningen

    $ wget http://github.com/technomancy/leiningen/raw/stable/bin/lein
    $ ./lein self-install

* git clone Chinarro

* Edit configuration

    $ emacs src/chinarro/configuration

* Compile

    $ lein deps
    $ lein compile
    $ lein uberjar

* Run Chinarro bot

    $ java -jar chinarro-standalone.jar bot

* Run Chinarro web

    $ java -jar chinarro-standalone.jar web

## Enjoy
![Sr. Chinarrro](http://proyectos.elcomerciodigital.com/blogs/musicom/files/2009/10/chinarro1.jpg)
