## Bonus

Soit la fonction Java suivante :

```Java
public void test(String a, String b) {
  System.out.println(a == b);
}

Si on appelle cette fonction, quel resultat sera affiché dans la console et pourquoi ?

Dans le cas ou les variables testées sont dans la stack de la mémoire :
se sont les pointeurs qui seront testés hors les pointeurs ne peuvent pas avoir la meme adresse.

Dans le cas ou les variables testées sont dans la heap de la mémoire :
le resultat du test sera correct.