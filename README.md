# Joystick-

Our application is an Android app Remote Control Joystick.
In the application we connect to a flight simulator (FlightGeer) by ip (of the computer that the fligtGeer runs) and port of the fligtGeer.
After the connection we can start the fly and control at the flying of the airplain by the joystick and the two sliders - throttle 
and rudder.
In our aplicattion we use MVVM architecture. 

In this [link](https://youtu.be/qwIGTnreLnA) you can see a video that explains a bit about this architecture in our project, 
the structure of the application and a demo of the application.

## Instructions:
1. First, you need to download fligtGeer at this [link](https://www.flightgear.org/)
2. Then, you need to download Android Studio IDE at this [link](https://developer.android.com/studio)
3. Now, open the flightGeer and go to Setting->Additional Settings and type this command:
   * --telnet=socket,in,10,127.0.0.1,6400,tcp
4. Download the files as zip files, and then extract them
4. Now, open the files in Android Studio : click on File -> Open -> the folder(Joystick) -> ok
6. In the flightGeer you need to press on 'fly' and then press on 'v' in your keyboard
7. Run the project in Android Studio
6. When the application's screen opens, enter the ip and port (6400), and then click on connect

UML file of our project ![uml](https://user-images.githubusercontent.com/73199918/123546206-518f9d80-d764-11eb-8015-d3ad60d3ae88.png)
Explanatory file on the classes in our code and a description of the MVVM architecture [Main Classes-MVVM.pdf](https://github.com/ShaharHarelWeiss/Joystick-/files/6721667/Main.Classes-MVVM.pdf)

Enjoy!!!

## Authors:
* Shahar Harel
* Efrat Naaman
