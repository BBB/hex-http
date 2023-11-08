{
  description = "BBB:hex-http";
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
  inputs.nixpkgs-temurin-21.url = "github:Infinidoge/nixpkgs/feat/temurin-bin-21";
  inputs.flake-utils.url = "github:numtide/flake-utils";

  outputs = { self, nixpkgs, nixpkgs-temurin-21, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system: let
      pkgs = nixpkgs.legacyPackages.${system};
      fork = nixpkgs-temurin-21.legacyPackages.${system};
    in {
      devShells.default = pkgs.mkShell rec {
        packages = with pkgs; [
          direnv
          gradle
          git
          fork.temurin-bin-20
        ];
      };
    });
}
