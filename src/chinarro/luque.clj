(ns chinarro.luque)

(def wisdom ["la tapia del perejil"
             "lerele"
             "pap&aacute; matem&aacute;ticas"
             "va la tuya y dejar&aacute; una raya de las suyas, quirom&aacute;ntico"
             "con su tira de la manta con su quinto trae el mantel"
             "don confitero un monje duerme en el armario"
             "un mudo con zambomba con z y con b"
             "cartucho!"
             "regalo mi disfraz de esp&iacute;a tuerto"
             "estupidos que son los reyes magos, olvidaron el libro de instrucciones"
             "su mapamundi, gracias"
             "m&aacute;s que estrellas n&uacute;meros en retah&iacute;la"
             "no s&eacute; qu&eacute; no s&eacute; cu&aacute;ntos"
             "te encontr&eacute; Merche * por azar y sacamos el marisco a pasear"
             "y tropieza el vendedor con el cat&aaacute;logo de plumas"
             "a Manolo el del Bombo de remate, no le salen las cuentas"
             "vamos de Cuba a Tailandia a por la vacuna, pinchate una tu"
             "pica el gallo Mor&oacute;n y en Portugal el gallo Claudio gallo, gallina"
             "han quedado n&iacute;quel"])

(defn random-musing []
  (let [max (count wisdom)
        rand (mod (int (* (Math/random) 100)) max)]
    (nth wisdom rand)))
