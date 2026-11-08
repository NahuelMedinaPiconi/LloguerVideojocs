# GestioVideojocs

Aplicació d'escriptori (Java Swing) per gestionar el lloguer de videojocs amb usuaris, jocs, plataformes i lloguers.

## Requisits

- **JDK 25** (el projecte compila amb `maven.compiler.release` 25)
- **Apache Maven** (inclòs en NetBeans)
- **NetBeans** (recomanació d'ús)
- **Instal·lar ValidationAPI** (https://github.com/NahuelMedinaPiconi/ValidationAPI)

## Estructura que ha de tenir el projecte: 

```
/ValidationAPI      Llibreria de validacions (s'ha d'instal·lar primer)
/GestioVideojocs    Aplicació principal
```

## Instal·lació

### 1. Instal·lar la llibreria ValidationAPI

L'aplicació depèn de `ValidationAPI`, cal instal·lar-la al Maven de GestioVideojocs.

**Amb NetBeans:** obrir el projecte `ValidationAPI`, clic dret → *Run Maven → Other Goals...* → escriure `clean install` → OK.

**Amb terminal:**

```
cd ValidationAPI
mvn clean install
```

Ha d'aparèixer `BUILD SUCCESS`.

### 2. Compilar l'aplicació

**Amb NetBeans:** obrir el projecte `GestioVideojocs`, clic dret → *Clean and Build*.

**Amb terminal:**

```
cd GestioVideojocs
mvn clean package
```

### 3. Executar

**Amb NetBeans:** obrir `MainScreen.java` i prémer *Run File* (Shift+F6), o *Run Project* (F6).

**Amb terminal:**

```
mvn exec:java -Dexec.mainClass=com.nahuelcompany.gestiovideojocs.gui.MainScreen
```

## Ús bàsic

1. **Usuari → Crear:** registrar un usuari (nom, cognoms, DNI i telèfon). El nom i els cognoms han de començar per majúscula, el DNI té el format `00000000X` i el telèfon només admet números i ha de tenir una longitud de 9 dígits.
2. **Joc → Crear:** registrar un joc amb el seu gènere i les plataformes disponibles amb les seves unitats.
3. **Lloguer → Registrar:** triar usuari, joc, plataforma i data de devolució.
4. **Lloguer → Tornar:** marcar un lloguer com a retornat (es reposa la unitat a l'estoc).
5. **Usuari / Joc → Borrar:** eliminar usuaris o jocs que no tinguin lloguers actius.

La pantalla principal mostra els lloguers amb l'estat en color: verd (dins de termini) i vermell (caducat).

> Les dades es guarden només en memòria: en tancar l'aplicació es perden i es carreguen de nou les dades d'exemple.

## Problemes freqüents

- **`Could not find artifact ...ValidationAPI`:** no s'ha instal·lat la llibreria. Repetir el pas 1.
- **`MissingResourceException ... Bundle`:** el fitxer `Bundle_ca.properties` ha d'estar a `src/main/resources/com/nahuelcompany/gestiovideojocs/`. Fer *Clean and Build*.
