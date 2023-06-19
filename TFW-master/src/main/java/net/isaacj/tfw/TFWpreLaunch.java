package net.isaacj.tfw;


import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class TFWpreLaunch implements PreLaunchEntrypoint {


    public void onPreLaunch(){

        MixinExtrasBootstrap.init();
    }


}
