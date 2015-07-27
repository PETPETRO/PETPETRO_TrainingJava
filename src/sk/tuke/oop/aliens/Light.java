/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.oop.aliens;

import sk.tuke.oop.aliens.actor.AbstractActor;
import sk.tuke.oop.framework.Animation;

/**
 *
 * @author galilei-08
 */
public class Light extends AbstractActor{
    
    private boolean electricity;
    private Reactor reactor;
    Animation lightOff=new Animation("resources/images/light_off.png", 16, 16, 10);
    Animation lightOn=new Animation("resources/images/light_on.png", 16, 16, 10);
    
    public Light(){
        setAnimation(lightOff);
    }
    
   
    public void toggle(){
        if(electricity==true ){
           if(getAnimation()==lightOff){ 
               setAnimation(lightOn);}
           else{setAnimation(lightOff);}
        }
        
        if(electricity==false){
            setAnimation(lightOff);
    }
        
    }
    
    
    public void  setElectricityFlow(boolean state){
        this.electricity=state;

//        if(this.electricity==true && state==true){
//            setAnimation(lightOn);
//        }else{
//            setAnimation(lightOff);}
      
    }
}
