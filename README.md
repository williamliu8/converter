<img src="https://drive.google.com/uc?export=view&id=1sbM4NR-Z8GMNZa-ljcZgZ2-AEyj4YpGx" width="256" title="Icon">  

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

1.Temperature  
* Select "Fahrenheit <-> Celsius" from the drop down menu.  
* Input a number  

|Screen Shot|Example|
|--------|--------|
|<img src="https://drive.google.com/uc?export=view&id=1Ukof4KV_R7K31NZCkzMPR3-3CWy0fk1e" width="256" title="Temperature">|<img src="https://drive.google.com/uc?export=view&id=1VQI3a320PpuIIfQr5qsM6BTEwFF_u_R7" width="256" title="Temperature EX">|  

2.Volume  
Input number in one of the unit, it will calculate and show you all other units.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://drive.google.com/uc?export=view&id=1UjarTEjhApVtlczEbsCsdhBCGuBxU1_0" width="256" title="Volume">|<img src="https://drive.google.com/uc?export=view&id=1VLrgiqM7LwxWizo8ZH7Z97CBd6-F3xbm" width="256" title="VolumeEX">|  

3.Weight  
Input number in one of the unit, it will calculate and show you all other units.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://drive.google.com/uc?export=view&id=1VC4DZUSlMdRYRLFzsf9ubvnXj2XfYRa9" width="256" title="Weight">|<img src="https://drive.google.com/uc?export=view&id=1VLXK_PWDwQD8J5YLiiKG65uBBM_1fjPU" width="256" title="WeightEX">|  

4.Area  
Input number in one of the unit, it will calculate and show you all other units.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://drive.google.com/uc?export=view&id=1V4zyUngPJMWp5aPY-bYeiyCnx27Tq_UF" width="256" title="Area">|<img src="https://drive.google.com/uc?export=view&id=1VJtzHrtV0p0zuaseOG7AWfeOmKA32WCL" width="256" title="Area">|  

5.Length  
* Select input unit from drop down menu.  
* Select output unit from drop down menu. 
* Input a number.  

|Screen Shot|Example|
|--------|--------|
|<img src="https://drive.google.com/uc?export=view&id=1V4g9Ag1XGMq7S7_H8bq8nRnAfB-wg8Z0" width="256" title="Length">|<img src="https://drive.google.com/uc?export=view&id=1VI62T0THa-nX69Q9KsjdqjLvuD6F8HCX" width="256" title="LengthEx">|  

6.Fraction multiplier  
As you can see, there is a yellow star in a red circle.  
It is a FAB (Float Action Button).  
Please refer to the website:  
[FAB](https://developer.android.com/guide/topics/ui/floating-action-button)  
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
|<img src="https://drive.google.com/uc?export=view&id=1Uv2z4-hxrtYjDSjOoeaeiXKie395i4te" width="256" title="Fraction Multiplier">|<img src="https://drive.google.com/uc?export=view&id=1VT7vP8guLaG5JDBKo4atwZd3CGbgIH5D" width="256" title="Fraction Multiplier EX">|  

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
