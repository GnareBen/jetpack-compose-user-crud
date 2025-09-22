# UserCRUD - Application Android

Application Android de gestion d'utilisateurs développée avec Jetpack Compose et architecture MVVM.

## 📱 Fonctionnalités

### Gestion des Utilisateurs
- **Ajout d'utilisateurs** : Création de nouveaux utilisateurs avec un formulaire complet
- **Affichage de la liste** : Visualisation de tous les utilisateurs enregistrés
- **Modification d'utilisateurs** : Édition des informations utilisateur existantes
- **Suppression d'utilisateurs** : Suppression des utilisateurs de la base de données
- **Recherche** : Fonctionnalité de recherche pour filtrer les utilisateurs

### Informations Utilisateur
Chaque utilisateur contient les informations suivantes :
- Prénom
- Nom de famille
- Adresse e-mail
- Numéro de téléphone
- Adresse
- Date de création automatique

### Interface Utilisateur
- **Design moderne** : Interface utilisateur avec Material Design 3
- **Navigation fluide** : Navigation entre les écrans avec Jetpack Navigation
- **Barre de recherche** : Recherche en temps réel dans la liste des utilisateurs
- **Bouton d'action flottant** : Accès rapide pour ajouter un nouvel utilisateur
- **Cartes utilisateur** : Affichage élégant des informations utilisateur
- **Messages de feedback** : Notifications de succès et d'erreur

## 🛠 Technologies Utilisées

### Architecture
- **MVVM** (Model-View-ViewModel)
- **Repository Pattern** pour la gestion des données
- **Dependency Injection** avec Dagger Hilt

### Interface Utilisateur
- **Jetpack Compose** pour l'interface utilisateur moderne
- **Material Design 3** pour le design system
- **Navigation Compose** pour la navigation

### Gestion des Données
- **Room Database** pour la persistance locale
- **DAO** (Data Access Object) pour les opérations de base de données
- **Flow** pour la programmation réactive

### Autres
- **Kotlin** comme langage principal
- **Coroutines** pour la programmation asynchrone
- **Gson** pour la sérialisation JSON
- **Coil** pour le chargement d'images

## 📋 Prérequis

- Android Studio Koala ou plus récent
- SDK Android minimum : API 24 (Android 7.0)
- SDK Android cible : API 35
- Kotlin 1.9+

## 🚀 Installation

1. Clonez le repository :
```bash
git clone <url-du-repository>
```

2. Ouvrez le projet dans Android Studio

3. Synchronisez les dépendances Gradle

4. Lancez l'application sur un émulateur ou un appareil physique

## 📱 Utilisation

1. **Ajouter un utilisateur** : Appuyez sur le bouton "+" flottant et remplissez le formulaire
2. **Voir les utilisateurs** : La liste s'affiche automatiquement sur l'écran principal
3. **Rechercher** : Utilisez la barre de recherche en haut pour filtrer les utilisateurs
4. **Modifier** : Appuyez sur l'icône d'édition d'une carte utilisateur
5. **Supprimer** : Appuyez sur l'icône de suppression d'une carte utilisateur

## 🏗 Structure du Projet

```
app/src/main/java/ci/gdevs/usercrud/
├── data/
│   └── local/
│       ├── dao/           # Data Access Objects
│       ├── database/      # Configuration Room
│       ├── entity/        # Entités de base de données
│       └── repository/    # Repositories
├── di/                    # Modules Dagger Hilt
├── navigation/            # Configuration de navigation
├── presentation/
│   ├── components/        # Composables réutilisables
│   ├── screens/          # Écrans de l'application
│   ├── theme/            # Thème et styles
│   ├── uiState/          # États de l'interface utilisateur
│   └── viewmodel/        # ViewModels
└── ui/                   # Activité principale
```

## 🎨 Captures d'Écran

*[Ajoutez ici des captures d'écran de votre application]*

## 📄 Licence

Ce projet est sous licence [choisir une licence].

## 👨‍💻 Développeur

Développé par [votre nom/organisation].