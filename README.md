# Utilities Minecraft Plugin

## Overview
Utilities is a comprehensive Spigot/Bukkit plugin that adds essential utility commands to your Minecraft server, making server management and player interaction more convenient.

## Features

### Player Utilities
- `/heal`: Restore player health completely
- `/fix`: Repair the item in your hand
- `/fixall`: Repair all items in your inventory
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

## Installation

### Requirements
- Spigot/Bukkit Server (1.16.5 or higher)
- Java 8+

### Steps
1. Download the latest `Utilities-1.0-SNAPSHOT.jar` from the releases
2. Place the JAR in your server's `plugins` directory
3. Restart or reload your server

## Permissions

### Player Permissions
- `utilities.heal`: Allow healing yourself
- `utilities.fix`: Repair items in hand
- `utilities.fixall`: Repair all inventory items
- `utilities.ping`: Use ping command
- `utilities.broadcast`: Send server-wide messages
- `utilities.serverinfo`: View server details

### Admin Permissions
- `utilities.reload`: Reload plugin configuration

## Configuration
The plugin generates a `config.yml` in the `Utilities` plugin folder. Customize settings as needed.

## Commands

### Player Commands
- `/heal`: Restore full health
- `/fix`: Repair item in hand
- `/fixall`: Repair all inventory items
- `/ping`: Connection check
- `/broadcast`: Send messages to all players
- `/serverinfo`: View server information

## Upcoming Features
- More utility commands
- Enhanced configuration options
- Performance improvements

## Support
- Report issues on GitHub
- Contact: singh-m21@ulster.ac.uk

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

## Author
Millen Singh (GitHub: @milnee)

---

*Crafted with ❤️ for Minecraft server administrators*
