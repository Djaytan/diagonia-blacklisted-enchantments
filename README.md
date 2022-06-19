# diagonia-blacklisted-enchantments
Diagonia's plugin which blacklist enchantments like Mending and Infinity in Minecraft

## How it works

At some events, checks are dispatch to remove and/or deactivate blacklisted enchantments :
* At preparing enchantment time (when we can see one enchantment before enchanting item)
* At enchantment time
* At repair time in anvil
* At loot time (entity death, chest loot, fish treasure, piglin barting)
* At villager trade acquiring (when villager spawn or unlock new trades)
* At shoot bow time
* At item mend time

When an item don't have any enchantment anymore after filtering process, the Durability one is
applied automatically. For this reason, it isn't recommended to blacklist this one.

This plugin take into account enchanting books as well.

## Illustration

In this example, when a player shoot with a bow whereas it has Infinity and Mending enchantments,
this item in the hand of the player is updated by removing these blacklisted enchantments.

![](img/bow_shoot_reaction.png)

## Licence

This project is under the licence GNU GPLv3.
