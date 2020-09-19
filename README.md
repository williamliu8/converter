<img src="https://user-images.githubusercontent.com/50005966/93687445-db426a00-fa72-11ea-89bd-6b08c360d64b.png" width="80" title="Icon">  

## Introduction  
This App can help you to do metric unit <-> imperial unit.  

I've tested functions with  
* Samsung Note 3  
  * 5.7" 1080 x 1920
* Note 8  
  * 6.3" 1440 x 2960
* Sony Xperia Z4 tablet  
  * 10.1" 2560 x 1600
* ZTE Amazing A30  
  * 5" 480 x 854  

## Functions  
1.Temperature  
* Select "Fahrenheit <-> Celsius" from the drop down menu.  
* Input a number  

|Screen Shot|Example|
|--------|--------|
|<img src="https://user-images.githubusercontent.com/50005966/93687490-3ecc9780-fa73-11ea-81fb-0dc0e4107234.jpg" width="256" title="Temperature">|<img src="https://user-images.githubusercontent.com/50005966/93687491-412ef180-fa73-11ea-8cde-2b2e979ca39e.jpg" width="256" title="TemperatureEX">|  

2.Volume  
Input number in one of the unit, it will calculate and show you all other units.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://user-images.githubusercontent.com/50005966/93687495-48ee9600-fa73-11ea-940b-c62d34f6f238.jpg" width="256" title="Volume">|<img src="https://user-images.githubusercontent.com/50005966/93687496-4d1ab380-fa73-11ea-9e7e-935238f13239.jpg" width="256" title="VolumeEX">|  

3.Weight  
Input number in one of the unit, it will calculate and show you all other units.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://user-images.githubusercontent.com/50005966/93687499-53a92b00-fa73-11ea-9aad-7b46829109a7.jpg" width="256" title="Weight">|<img src="https://user-images.githubusercontent.com/50005966/93687501-56a41b80-fa73-11ea-8155-2cb148733e6f.jpg" width="256" title="WeightEX">|  

4.Area  
Input number in one of the unit, it will calculate and show you all other units.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://user-images.githubusercontent.com/50005966/93687510-60c61a00-fa73-11ea-8d89-14e2b9670eb8.jpg" width="256" title="Area">|<img src="https://user-images.githubusercontent.com/50005966/93687514-63287400-fa73-11ea-995f-d065834162a8.jpg" width="256" title="AreaEX">|  

5.Length  
* Select input unit from drop down menu.  
* Select output unit from drop down menu. 
* Input a number.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://user-images.githubusercontent.com/50005966/93687477-31afa880-fa73-11ea-91dd-b9ba2ebe2b9b.jpg" width="256" title="Length">|<img src="https://user-images.githubusercontent.com/50005966/93687482-35dbc600-fa73-11ea-9576-d96d4debf592.jpg" width="256" title="LengthEx">|  

6.Fraction multiplier  
As you can see, there is a yellow star in a red circle.  
It is a FAB (Float Action Button).  
Please refer to the website:[FAB](https://developer.android.com/guide/topics/ui/floating-action-button)  
You can drag it any where on the screen.  
If you click on it, a red small calculator will show up.  
(Please note that a blank field is default to 1.)  

In order not to block the convert result, the multiplier is transparent.  
You can also drag it up and down.  

How to use it?  
For example:  
You live in Washington state, the sales tax is 10%.  
You found a product that is 50 INCHes for $30 and it is 20% off.  
What is the price per CM including discount and tax?  

1.Input 50 INCHes,you will get 127 CMs  
2.Click on the FAB  
* Input 30 to the first numerator, 127 to denominator.  
This is for price per CM.  
* Input 0.8 to the second numerator, leave denominator blank.(or 80 to numerator, 100 to denominator if you like)  
This is for 20% off discount.  
* Input 10 to Tax Rate(%)  
This is for 10% sales tax  
* You will get the result!  

|Screen Shot|Example|
|--------|--------|
|<img src="https://user-images.githubusercontent.com/50005966/93687556-79cecb00-fa73-11ea-9931-04e1c318cf35.jpg" width="256" title="Fraction Multiplier">|<img src="https://user-images.githubusercontent.com/50005966/93687532-6e7b9f80-fa73-11ea-85f1-340c85265580.jpg" width="256" title="Fraction MultiplierEX">|  

## General Keywords:  
* BottomNavigationView  
* Fragments(Temperature,Volume,Weight,Area,Length fragments)  
* FAB (Float Action Button)  
* TextView  
* ListView  
  * ArrayAdapter:  
  For drop down menu in Temperature and Length fragments.  
* ScrollView  
For low resolution devices.  
* ViewPager  
Slide left or right on screen to change different functions.  

Hope you enjoy it!  
