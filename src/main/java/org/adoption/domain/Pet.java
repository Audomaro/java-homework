package org.adoption.domain;

import com.github.javafaker.Faker;

public class Pet {

    public enum PetType {
        CAT,
        DOG,
        TURTLE
    }

    public enum BreedType {
        Buldog,
        Chihuahua,
        Dachshund,
        Poodle,
        Siamese,
        British_Shorthair,
        Sphynx,
        Ragdoll,
        Persian,
        Unknowk
    }

    private int petId;
    private String name;
    private PetType type;
    private BreedType breed;

    public Pet(int petId, String name, PetType type, BreedType breed) {
        this.petId = petId;
        this.name = name;
        this.type = type;
        this.breed = breed;
    }

    public Pet(int petId, String name, PetType type) {
        this(petId, name, type, BreedType.Unknowk);
    }

    public Pet(PetType type) {
        this(
                new Faker().random().nextInt(100),
                new Faker().funnyName().name(),
                type,
                BreedType.Unknowk
        );
    }

    public Pet() {

    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public BreedType getBreed() {
        return breed;
    }

    public void setBreed(BreedType breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", breed=" + breed +
                '}';
    }

    public  static PetBuilder builder() {
        return new PetBuilder();
    }
    ////////////////////////////////////

    public static class PetBuilder {
        private int petId;
        private String name;
        private PetType type;
        private BreedType breed;

        public PetBuilder(){

        }
        public PetBuilder petId(int petId) {
            this.petId = petId;
            return this;
        }

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder type(PetType type) {
            this.type = type;
            return this;
        }

        public PetBuilder breed(BreedType breed) {
            this.breed = breed;
            return this;
        }

        public Pet build() {

            if (petId == 0) {
                petId = new Faker().random().nextInt(100);
            }

            if (name == null) {
                name = new Faker().funnyName().name();
            }

            if (breed == null) {
                breed = BreedType.Unknowk;
            }

            return new Pet(petId, name, type, breed);
        }
    }
}
