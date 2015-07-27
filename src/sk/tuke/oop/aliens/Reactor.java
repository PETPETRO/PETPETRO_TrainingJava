/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.oop.aliens;

import sk.tuke.oop.framework.Animation;
import sk.tuke.oop.aliens.actor.AbstractActor;

/**
 *
 * @author galilei-08
 */
public class Reactor extends AbstractActor {

    private int temperature;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokAnimation;
    private int damage;
    private boolean state;
    private Animation ofAnimation;
    private Light light;

    public Reactor() {
        temperature = 0;
        damage = 0;
        state = false;

        // create animation object
        ofAnimation = new Animation("resources/images/reactor.png", 80, 80, 100);
        normalAnimation = new Animation("resources/images/reactor_on.png", 80, 80, 100);
        hotAnimation = new Animation("resources/images/reactor_hot.png", 80, 80, 50);
        brokAnimation = new Animation("resources/images/reactor_broken.png", 80, 80, 100);
        // play animation repeatedly
        normalAnimation.setPingPong(true);
        // set actor's animation to just created Animation object
        setAnimation(ofAnimation);
    }

    public int getDamage() {
        return damage;
    }

    /**
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setDamage(int damage) {
        this.damage = damage;
       this.light.setElectricityFlow(false);
    }

    //S nárastom teploty lineárne zvyšujte poškodenie reaktora. 
    //Reaktor sa začne kaziť po prekročení teploty 2000 stupňov a prestane byť funkčný po prekročení 5000 stupňov.
    // Ochladenie reaktora ale nezníži úroveň poškodenia, ktoré už vysoká teplota spôsobila.
    public void increaseTemperature(int increment) {
        if (state == false || increment < 0) {
            return;
        }
        if (increment < 0) {
            return;
        }
        temperature += increment;
        if (damage >= 50) {
            temperature += increment;
        }
        if (2000 < temperature && damage <= (int) (temperature - 2000) / 30) {
            damage = (int) (temperature - 2000) / 30;

        }

        if (4000 < temperature) {
            // set animation object
            updateAnimation();
        }

        if (temperature >= 5000) {
            damage = 100;
            turnOff();
            updateAnimation();
            light.setElectricityFlow(false);

        }

    }

    public void decreaseTemperature(int decrease) {
        if (state == false || decrease < 0) {
            return;
        }
        if (damage >= 100) {
            return;
        } else if (damage >= 50) {
            temperature -= decrease / 2;
        } else {
            temperature -= decrease;
        }
        if (temperature < 4000) {
            updateAnimation();
        }

    }

    private void updateAnimation() {
        if (temperature > 4000) {
            this.setAnimation(hotAnimation);
        }
        if (temperature >= 5000) {
            this.setAnimation(brokAnimation);
        } else {
            this.setAnimation(normalAnimation);
        }
    }

    public void repair(Hammer hammer) {
        if (hammer == null) {
            return;
        }
        if (temperature >= 1000) {
            temperature = 1000;
        }
        if (damage > 50) {
            damage = damage - 50;
        } else {
            damage = 0;
        }

        updateAnimation();
    }

    public void turnOn() {
        
            state = true;
            updateAnimation();
            getAnimation().start();
            light.setElectricityFlow(true);
        
    }

    public void turnOff() {
       
            state = false;
            getAnimation().stop();
            light.setElectricityFlow(false);
            light.setAnimation(light.lightOff);
        
    }

    public boolean isRunning() {
        return state;
    }
    
    public void addLight(Light light){
        this.light=light;
        light.setElectricityFlow(state);
    }
    
    public void removeLight(){
        this.light=null;
    }
}
