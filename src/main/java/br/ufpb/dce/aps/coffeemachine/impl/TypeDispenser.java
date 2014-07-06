package br.ufpb.dce.aps.coffeemachine.impl;

public enum TypeDispenser {
	COFFEE_POWDER("coffeePowder"), CUP("cup"), WATER("water"), SUGAR("sugar"), CREAMER("creamer");
		
		private final String type;

		private TypeDispenser(final String type) {
			this.type = type;
		}

		public String getValue() {
			return type;
		}
}
