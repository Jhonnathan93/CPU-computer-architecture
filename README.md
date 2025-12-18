## CPU-computer-architecture

Educational implementation of a simple 16-bit CPU in Java, including ALU, registers, program counter, ROM, RAM, and a control unit capable of executing a small instruction set (Hack-like).

### Overview

This project is a **from-scratch simulation of a basic 16-bit computer architecture in Java**.  
It models the classic components of a simple CPU and shows how machine instructions are fetched, decoded, and executed over a small RAM.

Core components (all in `src/components`):
- **Logic gates**: `And`, `Or`, `Not`
- **Word utilities**: `Word` (bit-level helpers)
- **Data path elements**: `Register`, `PC` (program counter), `RAM`, `ROM`, `Multiplexor`
- **ALU**: `ALU` (supports Hack-style ALU control bits, zero/neg flags)
- **Control unit**: `ControlUnit` (ties everything together and runs a small test program)

### Project structure

- **`src/components/ALU.java`**: 16-bit ALU with control bits (`zx`, `nx`, `zy`, `ny`, `f`, `no`) and flags (`zr`, `ng`).
- **`src/components/ControlUnit.java`**: Fetch–decode–execute loop; connects ROM, RAM, registers, ALU, and PC. Contains a `main` method with a sample program.
- **`src/components/RAM.java`**: 16K-word RAM with bounds-checked read/write.
- **`src/components/ROM.java`**: 32K-word instruction memory for the program.
- **`src/components/PC.java`**: Program counter with `inc`, `load`, and `reset`.
- **`src/components/Register.java`**: Simple 16-bit register with load control.
- **`src/components/And.java`, `Or.java`, `Not.java`**: Bitwise logic gates.
- **`src/components/Multiplexor.java`**: 16-bit 2-to-1 multiplexer.
- **`src/components/Word.java`**: Bit manipulation helpers (get, set, extract bits).

### How to run

1. **Compile** (from the project root):

```bash
javac src/components/*.java
```

2. **Run** the control unit simulation:

```bash
java -cp src/components ControlUnit
```

### Example program (already included)

Inside `ControlUnit.main`, the following program is loaded into ROM:
- `@0` / `D=M` – load value from `RAM[0]` into D
- `@1` / `D=D+M` – add `RAM[1]` to D
- `@2` / `M=D` – store result in `RAM[2]`

You can change the program by modifying the `rom.setInstruction(...)` calls and/or the initial RAM values.

### Use cases

- **Learning computer architecture** and the Hack-style 16-bit CPU.
- **Understanding how an ALU, PC, and control logic work together**.
- **Experimenting with simple instruction sets and execution flows** in Java.
