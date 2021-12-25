package com.chaosbuffalo.spartanfire.init;

import com.chaosbuffalo.spartanfire.SpartanFire;
import com.chaosbuffalo.spartanfire.Utils;
import com.chaosbuffalo.spartanfire.integrations.*;
import com.chaosbuffalo.spartanfire.items.SFItem;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Jacob on 7/20/2018.
 */
@Mod.EventBusSubscriber(modid = SpartanFire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistrySFire {
    private static final Logger logger = LogManager.getLogger(SpartanFire.MODID);

    public static final Set<WeaponMaterial> MATERIALS_TO_REGISTER = new LinkedHashSet<>();
    public static final String DRAGONBONE = "dragonbone";
    public static final String DRAGONBONE_FIRE = "dragonbone_fire";
    public static final String DRAGONBONE_ICE = "dragonbone_ice";
    public static final String DRAGONBONE_LIGHTNING = "dragonbone_lightning";
    public static final String MYRMEXJUNGLE = "myrmexjungle";
    public static final String MYRMEXJUNGLE_VENOM = "myrmexjungle_venom";
    public static final String MYRMEXDESERT = "myrmexdesert";
    public static final String MYRMEXDESERT_VENOM = "myrmexdesert_venom";
    public static final String DRAGONSTEEL_FIRE = "dragonsteel_fire";
    public static final String DRAGONSTEEL_ICE = "dragonsteel_ice";
    public static final String DRAGONSTEEL_LIGHTNING = "dragonsteel_lightning";

    private static final Set<Item> ALL_ITEMS = new HashSet<>();

    static {
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONBONE,
                        IafItemRegistry.DRAGONBONE_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonbone"
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONBONE_FIRE,
                        IafItemRegistry.FIRE_DRAGONBONE_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonbone",
                        new FireSwordWeaponProperty(DRAGONBONE_FIRE, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONBONE_ICE,
                        IafItemRegistry.ICE_DRAGONBONE_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonbone",
                        new IceSwordWeaponProperty(DRAGONBONE_ICE, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONBONE_LIGHTNING,
                        IafItemRegistry.LIGHTNING_DRAGONBONE_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonbone",
                        new LightningSwordWeaponProperty(DRAGONBONE_LIGHTNING, SpartanFire.MODID)));
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        MYRMEXJUNGLE,
                        IafItemRegistry.MYRMEX_CHITIN_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "myrmex_jungle_chitin",
                        new MyrmexSwordProperty(MYRMEXJUNGLE, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        MYRMEXDESERT,
                        IafItemRegistry.MYRMEX_CHITIN_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "myrmex_desert_chitin",
                        new MyrmexSwordProperty(MYRMEXDESERT, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        MYRMEXJUNGLE_VENOM,
                        IafItemRegistry.MYRMEX_CHITIN_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "myrmex_jungle_chitin",
                        new MyrmexSwordProperty(MYRMEXJUNGLE, SpartanFire.MODID),
                        new MyrmexPoisonSwordProperty(MYRMEXJUNGLE_VENOM, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        MYRMEXDESERT_VENOM,
                        IafItemRegistry.MYRMEX_CHITIN_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "myrmex_desert_chitin",
                        new MyrmexSwordProperty(MYRMEXDESERT, SpartanFire.MODID),
                        new MyrmexPoisonSwordProperty(MYRMEXDESERT_VENOM, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONSTEEL_ICE,
                        IafItemRegistry.DRAGONSTEEL_ICE_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonsteel_ice_ingot",
                        new IceDragonsteelWeaponProperty(DRAGONSTEEL_ICE, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONSTEEL_FIRE,
                        IafItemRegistry.DRAGONSTEEL_FIRE_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonsteel_fire_ingot",
                        new FireDragonsteelWeaponProperty(DRAGONSTEEL_FIRE, SpartanFire.MODID)
                )
        );
        MATERIALS_TO_REGISTER.add(
                Utils.spartanMatFromToolMat(
                        DRAGONSTEEL_LIGHTNING,
                        IafItemRegistry.DRAGONSTEEL_LIGHTNING_TOOL_MATERIAL,
                        9867904,
                        14999238,
                        "dragonsteel_lightning_ingot",
                        new LightningDragonsteelWeaponProperty(DRAGONSTEEL_LIGHTNING, SpartanFire.MODID)));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Set<Item> item_set = new LinkedHashSet<>();
        Item witherboneHandle = new SFItem(new ResourceLocation(SpartanFire.MODID, "witherbone_handle"),
                IceAndFire.TAB_ITEMS);
        Item witherbonePole = new SFItem(new ResourceLocation(SpartanFire.MODID, "witherbone_pole"),
                IceAndFire.TAB_ITEMS);
        ALL_ITEMS.add(witherboneHandle);
        ALL_ITEMS.add(witherbonePole);
        for (WeaponMaterial mat : MATERIALS_TO_REGISTER) {
            Item katana = SpartanWeaponryAPI.createKatana(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(katana);
            Item greatsword = SpartanWeaponryAPI.createGreatsword(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(greatsword);
            Item longsword = SpartanWeaponryAPI.createLongsword(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(longsword);
            Item saber = SpartanWeaponryAPI.createSaber(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(saber);
            Item rapier = SpartanWeaponryAPI.createRapier(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(rapier);
            Item dagger = SpartanWeaponryAPI.createDagger(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(dagger);
            Item spear = SpartanWeaponryAPI.createSpear(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(spear);
            Item pike = SpartanWeaponryAPI.createPike(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(pike);
            Item lance = SpartanWeaponryAPI.createLance(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(lance);
            Item halberd = SpartanWeaponryAPI.createHalberd(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(halberd);
            Item warhammer = SpartanWeaponryAPI.createWarhammer(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(warhammer);
            Item hammer = SpartanWeaponryAPI.createBattleHammer(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(hammer);
            Item throwing_axe = SpartanWeaponryAPI.createTomahawk(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(throwing_axe);
            Item throwing_knife = SpartanWeaponryAPI.createThrowingKnife(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(throwing_knife);
            Item longbow = SpartanWeaponryAPI.createLongbow(
                    mat,
                    IceAndFire.TAB_ITEMS
            );

            item_set.add(longbow);
            Item crossbow = SpartanWeaponryAPI.createHeavyCrossbow(
                    mat,
                    IceAndFire.TAB_ITEMS
            );

            item_set.add(crossbow);
            Item javelin = SpartanWeaponryAPI.createJavelin(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(javelin);
            Item battleaxe = SpartanWeaponryAPI.createBattleaxe(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(battleaxe);
            Item boomerang = SpartanWeaponryAPI.createBoomerang(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(boomerang);
            Item mace = SpartanWeaponryAPI.createFlangedMace(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(mace);
            Item quarterstaff = SpartanWeaponryAPI.createQuarterstaff(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(quarterstaff);
            Item glaive = SpartanWeaponryAPI.createGlaive(
                    mat,
                    IceAndFire.TAB_ITEMS
            );
            item_set.add(glaive);
        }
        try {
            Field registryNameField = ForgeRegistryEntry.class.getDeclaredField("registryName");
            registryNameField.setAccessible(true);

            item_set.forEach(item -> {
                ResourceLocation registryName = item.getRegistryName();
                if (registryName != null && !SpartanFire.MODID.equals(registryName.getNamespace())) {
                    try {
                        registryNameField.set(item, null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    item.setRegistryName(SpartanFire.MODID, registryName.getPath());
                }
            });
            registryNameField.setAccessible(false);


        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        ALL_ITEMS.forEach(event.getRegistry()::register);
        item_set.forEach(event.getRegistry()::register);

        logger.info("SpartanFire: registerItems");

//        printTranslate(item_set);
//        printTranslateEn(item_set);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ALL_ITEMS.stream()
                .filter(item -> item.getRegistryName() != null)
                .forEach(item -> new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    private static void printTranslate(Collection<Item> items) {
        JsonObject jsonObject = new JsonObject();
        Map<String, String> typeMap = new HashMap<String, String>();
        typeMap.put(".katana_", "太刀");
        typeMap.put(".greatsword_", "大剑");
        typeMap.put(".longsword_", "长剑");
        typeMap.put(".saber_", "军刀");
        typeMap.put(".rapier_", "迅捷剑");
        typeMap.put(".dagger_", "匕首");
        typeMap.put(".spear_", "枪");
        typeMap.put(".pike_", "长矛");
        typeMap.put(".lance_", "骑枪");
        typeMap.put(".halberd_", "战戟");
        typeMap.put(".warhammer_", "战锤");
        typeMap.put(".hammer_", "锤");
        typeMap.put(".tomahawk_", "印第安战斧");
        typeMap.put(".throwing_knife_", "飞刀");
        typeMap.put(".longbow_", "长弓");
        typeMap.put(".heavy_crossbow_", "重弩");
        typeMap.put(".javelin_", "标枪");
        typeMap.put(".battleaxe_", "战斧");
        typeMap.put(".boomerang_", "回力镖");
        typeMap.put(".flanged_mace_", "钉头锤");
        typeMap.put(".quarterstaff_", "长棍");
        typeMap.put(".glaive_", "长柄刀");

        Map<String, String> materialMap = new HashMap<String, String>();
        materialMap.put("dragonbone_fire", "火龙骨");
        materialMap.put("dragonbone_ice", "冰龙骨");
        materialMap.put("dragonbone_lightning", "雷霆龙骨");
        materialMap.put("dragonbone", "龙骨");
        materialMap.put("myrmexjungle_venom", "林蚁蛰针");
        materialMap.put("myrmexdesert_venom", "沙蚁蛰针");
        materialMap.put("myrmexjungle", "林蚁");
        materialMap.put("myrmexdesert", "沙蚁");
        materialMap.put("dragonsteel_ice", "龙霜钢");
        materialMap.put("dragonsteel_fire", "龙炎钢");
        materialMap.put("dragonsteel_lightning", "龙霆钢");

        items.forEach(item -> {
                    String key = item.getTranslationKey();
                    String value = "";
                    for (Map.Entry<String, String> entry : typeMap.entrySet()) {
                        if(key.contains(entry.getKey())) {
                            value = entry.getValue();
                            break;
                        }
                    }
                    for (Map.Entry<String, String> entry : materialMap.entrySet()) {
                        if(key.endsWith(entry.getKey())) {
                            value = entry.getValue() + value;
                        }
                    }
                    jsonObject.addProperty(key, value);
                }
        );
        logger.info(jsonObject.toString());
    }

    private static void printTranslateEn(Collection<Item> items) {
        JsonObject jsonObject = new JsonObject();
        Map<String, String> typeMap = new HashMap<String, String>();
        typeMap.put(".katana_", "Katana");
        typeMap.put(".greatsword_", "Greatsword");
        typeMap.put(".longsword_", "Longsword");
        typeMap.put(".saber_", "Saber");
        typeMap.put(".rapier_", "Rapier");
        typeMap.put(".dagger_", "Dagger");
        typeMap.put(".spear_", "Spear");
        typeMap.put(".pike_", "Pike");
        typeMap.put(".lance_", "Lance");
        typeMap.put(".halberd_", "Halberd");
        typeMap.put(".warhammer_", "Warhammer");
        typeMap.put(".hammer_", "Battle Hammer");
        typeMap.put(".tomahawk_", "Tomahawk");
        typeMap.put(".throwing_knife_", "Throwing Knife");
        typeMap.put(".longbow_", "Longbow");
        typeMap.put(".heavy_crossbow_", "Heavy Crossbow");
        typeMap.put(".javelin_", "Javelin");
        typeMap.put(".battleaxe_", "Battleaxe");
        typeMap.put(".boomerang_", "Boomerang");
        typeMap.put(".flanged_mace_", "Flanged Mace");
        typeMap.put(".quarterstaff_", "Quarterstaff");
        typeMap.put(".glaive_", "Glaive");

        Map<String, String> materialMap = new HashMap<String, String>();
        materialMap.put("dragonbone_fire", "Flamed Dragonbone ");
        materialMap.put("dragonbone_ice", "Iced Dragonbone ");
        materialMap.put("dragonbone_lightning", "Lightning Dragonbone");
        materialMap.put("dragonbone", "Dragonbone ");
        materialMap.put("myrmexjungle_venom", "Jungle Myrmex Stinger ");
        materialMap.put("myrmexdesert_venom", "Desert Myrmex Stinger ");
        materialMap.put("myrmexjungle", "Jungle Myrmex ");
        materialMap.put("myrmexdesert", "Desert Myrmex ");
        materialMap.put("dragonsteel_ice", "Ice Dragonsteel ");
        materialMap.put("dragonsteel_fire", "Fire Dragonsteel ");
        materialMap.put("dragonsteel_lightning", "Lightning Dragonsteel");

        items.forEach(item -> {
                    String key = item.getTranslationKey();
                    String value = "";
                    for (Map.Entry<String, String> entry : typeMap.entrySet()) {
                        if(key.contains(entry.getKey())) {
                            value = entry.getValue();
                            break;
                        }
                    }
                    for (Map.Entry<String, String> entry : materialMap.entrySet()) {
                        if(key.endsWith(entry.getKey())) {
                            value = entry.getValue() + value;
                        }
                    }
                    jsonObject.addProperty(key, value);
                }
        );
        logger.info(jsonObject.toString());
    }
}
