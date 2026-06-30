#!/bin/bash
set -e

# Build the Shadow JAR
echo "Building AEDesignSystem CLI Shadow JAR..."
./gradlew :cli:shadowJar

# Target directory for the binary
INSTALL_DIR="/usr/local/bin"
JAR_SOURCE="cli/build/libs/aedesignsystem.jar"
JAR_TARGET="/usr/local/share/aedesignsystem/aedesignsystem.jar"

echo "Installing jar file to /usr/local/share/aedesignsystem/..."
sudo mkdir -p /usr/local/share/aedesignsystem
sudo cp "$JAR_SOURCE" "$JAR_TARGET"

echo "Creating launcher script in $INSTALL_DIR/aedesignsystem..."
sudo tee "$INSTALL_DIR/aedesignsystem" > /dev/null << 'EOF'
#!/bin/bash
java -jar /usr/local/share/aedesignsystem/aedesignsystem.jar "$@"
EOF

sudo chmod +x "$INSTALL_DIR/aedesignsystem"

echo ""
echo "AEDesignSystem CLI installed successfully! Try running:"
echo "  aedesignsystem --help"
echo ""
