# Brown Dust 2 Automation Tool
Brown Dust 2 Automatic Script Tool:

> Based on OpenCV, this tool performs click operations by calculating graphic positions through screen captures.
> Since the project is not packaged, it can be customized/modified. Users may need to be familiar with Java.
> You may also contact me for packaging, processing, and debugging.

## How to Use (PC only)
> Requires Java environment: java17 (JDK is not required if packaged as an .exe)

## Feature List
* [X] Automatic Equipment Synthesis & Decomposition (Dust farming; levels need to be adjusted manually)
* [X] Automatic Book of Doomsday farming
* [ ] Automatic Map Clearing (Currently, script recording for Chapter 1 is completed. It can only run and cannot use Space to accelerate, but a script recording tool has been added to support custom recording scripts: found under `/GameUtils/ScripRecordingTool/`. I will upload the rest as I progress through the game)
* [ ] UI Script Creator (In progress)
* [ ] Quick Coupon Redemption

### 1. Automatic Equipment Synthesis & Decomposition
> 1. When using, open the Equipment interface, enter the selection screen to choose the equipment you want to batch-decompose, and configure the equipment settings.
>
> 2. Set the game to full screen (mine is 1920 * 1080). If it fails to recognize images, replace them yourself in the following path:
> `/BD2Automatic/images/Equipmentdecomposition/`
>
> Setting image references:
>
> <img src="https://github.com/1723283961/BD2AutomaticDecompositionEquipment/blob/kutori/%E5%BC%BA%E5%8C%96%E8%A3%85%E5%A4%87%E7%95%8C%E9%9D%A2.png" width="250px" alt="1">
> <img src="https://github.com/1723283961/BD2AutomaticDecompositionEquipment/blob/kutori/%E9%80%89%E6%8B%A9%E8%A3%85%E5%A4%87.png" width="250px" alt="2">
> <img src="https://github.com/1723283961/BD2AutomaticDecompositionEquipment/blob/kutori/%E5%88%86%E8%A7%A3%E8%A3%85%E5%A4%87%E9%85%8D%E7%BD%AE%E9%80%89%E6%8B%A9.png" width="250px" alt="3">
>
> Instructions: First, enter Equipment Selection and select equipment (lowest grade recommended). Then enter the enhancement level settings (level 7 recommended). Finally, return to the Equipment page and start the script (the interface shown in the second image).

### 2. Automatic Book of Doomsday Farming
![img.png](末日之书界面.png)
> 1. When using, open the Book of Doomsday interface and start the program (set to full screen; mine is 1920 * 1080).
>
> The program only needs to be able to read the "Battle" button in the interface; otherwise, you can modify the screenshots yourself.

## Reminders
* The `config.properties` file under `src/main/resources/` is the configuration file; please modify it as needed.
* The `logback.xml` file under `src/main/resources/` is for the log recording function and can be deleted.
* The images under `src/main/resources/images/Equipmentdecomposition/` are recognition images. If images cannot be matched, you can replace them, but the filenames must remain the same.
* If you encounter any issues, please submit them, and I will reply promptly.

## Friendly Reminder
### License
The code of this project can be used for **learning / research / personal use**.
Except for the author ([1723283961]), **any form of commercial use is prohibited**.

The [OpenCV](https://opencv.org/) library depended upon by this project follows the Apache License 2.0; its use must adhere to the original open-source license.
