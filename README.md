## Exchange console app

![Static Badge](https://img.shields.io/badge/Estado-completo-blue)

Aplicación de consola con funcionalidad para convertir diferentes divisas. Se hace uso de la api [ExchangeRate-API](https://www.exchangerate-api.com/)

## Visualización en consolas que soportan ANSI

<div style="display: flex; gap: 10px;">
    <div>
        <img src="/src/assests/mainMenu.png" alt="Menu principal" width: 10%">
    </div>
    <div>
        <img src="/src/assests/convertMenu.png" alt="Menu para seleccionar moneda">
    </div>
    <div>
        <img src="/src/assests/input.png" alt="input">
    </div>
    <div>
        <img src="/src/assests/result.png" alt="vista de resultado">
    </div>
</div>

## Visualización en consolas simples

<div style="display: flex; gap: 10px;">
    <div>
        <img src="/src/assests/basicMain.png" alt="Menu principal" width: 10%">
    </div>
    <div>
        <img src="/src/assests/basicConvertMenu.png" alt="Menu para seleccionar moneda">
    </div>
    <div>
        <img src="/src/assests/basicResult.png" alt="vista de resultado">
    </div>
</div>

## Funcionalidades del proyecto

- `Funcionalidad 1`: Conversión entre 20 tipos diferentes de divisas.
- `Funcionalidad 2`: Ver historial de conversiones.

## Acceso al proyecto

- Clonar repositorio en la maquina local.
- Tener JDK v17 o superior.
- Tener Maven instalado. En sus ultimas versiones.

## Ejecución

- Ubicarse en la carpeta que contiene el proyecto clonado.
- Ejecutar los comandos:
  - mvn clean
  - mvn compile
  - mvn exec:java -Dexec.mainClass=Main    

## Tecnologias usadas

- Java JDK 22
- mvn 3.9.9
- Jline 3.27.0
- Jansi 2.4.0
