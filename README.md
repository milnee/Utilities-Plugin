# Utilities Minecraft Plugin

## Overview
Utilities is a comprehensive Spigot/Bukkit plugin that adds essential utility commands to enhance server management and player experience.

## Features

### Player Utilities
- `/heal`: Restore player health completely
- `/fix`: Repair the item in your hand
- `/fixall`: Repair all items in your inventory
- `/seen`: Check when a player was last online
- `/feed`: Restore player hunger
- `/ping`: Simple connection check

### Server Management
- `/broadcast`: Send colorful messages to all players
  - Supports color codes using `&` prefix
  - Example: `/broadcast &1Blue message`
- `/serverinfo`: Get detailed server information
  - Shows server IP and port
  - Displays server version
  - Lists online players
- `/utilreload`: Reload plugin configuration
- `/clearlag`: Remove dropped items and clear server lag

## Installation

### Requirements
- Spigot/Bukkit Server (1.7.10)
- Java 7+

### Steps
1. Download the latest `Utilities-1.0-SNAPSHOT.jar` from the releases
2. Place the JAR in your server's `plugins` directory
3. Restart or reload your server

## Permissions

### Player Permissions
- `util.heal`: Allow healing yourself
- `util.fix`: Repair items in hand
- `util.fixall`: Repair all inventory items
- `util.ping`: Use ping command
- `util.seen`: Check player last online time
- `util.feed`: Restore hunger
- `util.broadcast`: Send server-wide messages
- `util.serverinfo`: View server details

### Admin Permissions
- `util.reload`: Reload plugin configuration
- `util.clearlag`: Clear server entities

## Configuration
The plugin generates a `config.yml` in the `Utilities` plugin folder. Customize settings as needed.

## Commands

### Player Commands
- `/heal`: Restore full health
- `/fix`: Repair item in hand
- `/fixall`: Repair all inventory items
- `/ping`: Connection check
- `/seen <player>`: Check player's last online time
- `/feed`: Restore hunger
- `/broadcast`: Send messages to all players
- `/serverinfo`: View server information
- `/clearlag`: Remove dropped items and clear server lag

## Upcoming Features
- More utility commands
- Enhanced configuration options
- Performance improvements

## Support
- Report issues on GitHub

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

## Author
Millen Singh (GitHub: @milnee)


