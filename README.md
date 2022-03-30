# LeBonCoin
c'est une application qui permet d'afficher une liste d'article, réalisée dans le cadre d'un test technique


 <img src="https://github.com/essid-ahmed/LeBonCoin/blob/main/albums_list_screen.png" alt="drawing" width="200" title="ecran d'acceuil" />
              <img src="https://github.com/essid-ahmed/LeBonCoin/blob/main/album_details_screen.png" alt="drawing" width="200" title="details d'un article" />
              


## Environnement technique
* Kotlin
## librairies externes
* dagger, hilt
* rx
* Retrofit, okhttp, Gson
* room
* mockito, espresso
* Picasso
## Architecture 
vue la contrainte de la gestion manuelle de la rotation de l'écran, je n'ai pas utilisé l'architecture a composont de google avec le livedata et le viewmodel, du coup j'ai implementé la clean architecture avec des composants qui n'héritent pas de la lifecycle librairie.



