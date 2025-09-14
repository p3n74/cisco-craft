# 🎮 Cisco Craft Mod - Testing Guide

## ✅ Your Mod is Ready!

**File**: `cisco_craft-1.0.0.jar` (17.6 KB)
**Location**: `/Users/bastionii/Documents/GitHub/cisco-craft/cisco_craft-1.0.0.jar`

## 🚀 How to Test in Minecraft

### Step 1: Install Minecraft Forge

1. **Download Forge 1.21.8** (version 58.1.1)
   - Go to: https://files.minecraftforge.net/net/minecraftforge/forge/index_1.21.8.html
   - Download the **Installer** (not the Universal JAR)
   - Run the installer and select "Install client"

### Step 2: Install Your Mod

1. **Find your Minecraft mods folder**:
   - **Windows**: `%appdata%\.minecraft\mods\`
   - **macOS**: `~/Library/Application Support/minecraft/mods/`
   - **Linux**: `~/.minecraft/mods/`

2. **Copy the mod file**:
   ```bash
   # Copy cisco_craft-1.0.0.jar to your mods folder
   cp cisco_craft-1.0.0.jar ~/Library/Application\ Support/minecraft/mods/
   ```

### Step 3: Launch Minecraft

1. **Open Minecraft Launcher**
2. **Select Forge Profile**: Choose the "Forge" profile (not vanilla)
3. **Launch**: Start Minecraft with Forge

### Step 4: Test Your Mod

Once in-game, you should see:

#### ✅ Mod Loading Confirmation
- Check the console/logs for: `"CISCO CRAFT MOD INITIALIZED"`
- Check for: `"CISCO CRAFT CLIENT SETUP"`

#### 🎯 New Items & Blocks
1. **Open Creative Mode** (press `T` and type `/gamemode creative`)
2. **Look for "Cisco Tab"** in the creative inventory
3. **Find these new items**:
   - **Cisco Block**: A stone-like block
   - **Network Cable**: A food item (edible)

#### 🏗️ Creative Tab
- The "Cisco Tab" should appear **before** the Redstone tab
- Contains both the Cisco Block and Network Cable

## 🔍 What to Look For

### ✅ Success Indicators
- Mod appears in the mod list (Mods menu)
- "Cisco Craft" shows up in the mod list
- New creative tab is visible
- Items can be placed/used without crashes

### ❌ Troubleshooting
- **Mod not loading**: Check Forge version matches (1.21.8, 58.1.1)
- **Crashes**: Check Minecraft logs for errors
- **Missing items**: Ensure you're in Creative mode and looking in the right tab

## 📋 Mod Features

Your Cisco Craft mod includes:

- **Mod ID**: `cisco_craft`
- **Display Name**: "Cisco Craft"
- **Version**: 1.0.0
- **Author**: Cisco Team
- **Description**: "Cisco Craft - A Minecraft mod for networking and automation"

### Items Added:
1. **Cisco Block** (`cisco_craft:cisco_block`)
   - Stone-like block
   - Appears in Building Blocks tab
   - Can be placed and mined

2. **Network Cable** (`cisco_craft:network_cable`)
   - Edible item (nutrition: 1, saturation: 2)
   - Appears in Cisco Tab
   - Can be eaten for food

3. **Cisco Tab** (`cisco_craft:cisco_tab`)
   - Custom creative mode tab
   - Positioned before Redstone tab
   - Contains both mod items

## 🎉 Ready to Test!

Your mod is now ready for testing in Minecraft! The Docker build process successfully created a working mod that should load without issues on any platform (Windows, macOS, Linux).

**Happy Crafting!** 🚀