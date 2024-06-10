ZOMBIE SWARM

Description
- Zombie Swarm is a top down shooter game where your goal is to survive as long as possible and obtain the highest score you can possibly acheive
- use WASD to move your character around the screen
- use Left click on mouse to shoot at zombies
- use Q and E to switch between different guns you pickup
- you start with a simple pistol, but as you collect more coins, your coin bar will fill up - eventually spawning a random gun on the screen for you to pickup and use
- Zombies health and the requirment of coins for a new gun increase as time goes on, so getting good guns early on is key
- Collect powerups that randomly spawn on fixed powerup holes in the ground:
	- increased speed
	- increased health
 	- increased ammo

Problems encountered
- making sure the trajectory of the gun shooting was accurate to where the mouse is, as integer concatenation loses data at further distances
> this was solved by not only seperating horizontal and vertical movement, but also not fully moving by dy/dx every update
- making sure that the guns are properly spawned on the screen and added to the hero
> solved by using arrayLists to store the guns in GamePanel for easy access--did this for powerups and coins 
- animating the way at which the zombie is facing and making sure they are always facing/running towards the humans
> solved movement by the same logic by which the bullet shoots. Using Cos and Sin to calculate where the zombie needs to go
> solved animation by comparing dx and dy to find where the zombie is actually running
- The hero boundaries was not working as intended because the actual image had a little room around it
> solved by using resources to figure out how to slice a image to be just the hero
- Sound was making the game super laggy and slow, but was easily solved because I was creating new Clips for every sound instead of setting a sound file for one Clip
- Had to convert sound files from mp3 to wav because sound was not being read properly
 

Unimplemented features
- different projectile speed/types
> the different logic needed for each bullet speed and type would require an entire revamp of the bullet physics (time)
- the burst assault rifle being burst, meaning shooting three bullets at once 
> similar to bullet type issues, but rewriting all our code for one gun didnt make sense (time)
- gun attatchments
> adding different attatchments to guns would not only be difficult to balance, but also difficult to setup (time and complexity)
- certain guns
> we felt certain guns were too similar to other guns, so we chose to simply leave them out
-Adding a diffrent sound for every gun had worries of maybe more lag, (game lags at bit at first)

Reflection
- learned how to implement sound properly
- learned how to implement timers better
- learned how to use Sin and Cos and use movement at all angles
- learned how deal with several lag errors
- learned how to animate hero, zombies and coins comfortably 
- learned how to efficiently connect and work on project as a team
Overall we are very happy with our selection for this project, as the skills we have used we will be able to apply in all areas of coding no matter what field we go into.