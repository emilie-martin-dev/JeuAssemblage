package piece_puzzle.model;

import java.util.ArrayList;
import java.util.List;
import piece_puzzle.actions.ActionPiecePlace;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.observer.IPlateauListener;

/**
 * Représente un plateau de pièce
 */
public class Plateau implements IEnsembleBlocs {

	/**
	 * Liste de listeners
	 */
	private List<IPlateauListener> m_listeners;

	/**
	 * Les pièces du plateau
	 */
	private List<AbstractPiece> m_pieces;
	/**
	 * Largeur de la pièce
	 */
	private int m_width;
	/**
	 * Hauteur de la pièce
	 */
	private int m_height;

	public Plateau() {
		this(10, 10);
	}
	
	public Plateau(int w, int h) {
		m_width = w;
		m_height = h;
		
		m_pieces = new ArrayList<>();
		m_listeners = new ArrayList<>();
	}

	/**
	 * Permet l'ajout d'une pièce
	 * @param p La pièce à ajouter
	 */
	public void addPiece(AbstractPiece p) {
		addPiece(p, m_pieces.size());
	}

	/**
	 * Permet l'ajout d'une pièce à un emplacement précis
	 * @param p La pièce à ajouter
	 * @param index L'emplacement de la nouvelle pièce
	 */
	public void addPiece(AbstractPiece p, int index) {
		ActionPiecePlace placement = new ActionPiecePlace(this, p, index);
		
		if(placement.isValid()) {
			placement.apply();
		}
	}

	/**
	 * Permet la suppression d'une pièce
	 * @param p La pièce à supprimer
	 * @return L'indice de la pièce supprimée
	 */
	public int removePiece(AbstractPiece p) {
		int index = m_pieces.indexOf(p);
		m_pieces.remove(p);

		return index;
	}

	@Override
	public boolean isCaseFilledAt(int x, int y) {
		if(x < 0 || x >= m_width ||
			y < 0 || y >= m_height) {
			return true;
		}
		
		for(AbstractPiece p : m_pieces) {
			if(p.getPosition().getX() <= x 
					&& p.getPosition().getX() + p.getWidth() >= x
					&& p.getPosition().getY() <= y
					&& p.getPosition().getY() + p.getHeight() >= y) {
				if(p.isCaseFilledAt(x - p.getPosition().getX(),
						y - p.getPosition().getY())) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Retourne la pièce à la position x et y
	 * @param x Position x
	 * @param y Position y
	 * @return La pièce à la position x et y
	 */
	public AbstractPiece getPieceAt(int x, int y) {
		if(x < 0 || x >= m_width ||
			y < 0 || y >= m_height) {
			return null;
		}
		
		for(AbstractPiece p : m_pieces) {
			if(p.getPosition().getX() <= x 
					&& p.getPosition().getX() + p.getWidth() >= x
					&& p.getPosition().getY() <= y
					&& p.getPosition().getY() + p.getHeight() >= y) {
				if(p.isCaseFilledAt(x - p.getPosition().getX(),
						y - p.getPosition().getY())) {
					return p;
				}
			}
		}
		
		return null;
	}

	/**
	 * Ajoute un listener
	 * @param listener Le listener
	 */
	public void addListener(IPlateauListener listener) {
		m_listeners.add(listener);
	}

	/**
	 * Supprime un listener
	 * @param listener Le listener
	 */
	public void removeListener(IPlateauListener listener) {
		m_listeners.remove(listener);
	}

	/**
	 * Notifie l'ajout d'une pièce
	 * @param p La pièce ajouté
	 */
	public void firePieceAdded(AbstractPiece p) {
		for(IPlateauListener l : m_listeners) {
			l.pieceAdded(p);
		}
	}

	/**
	 * Notifie la suppression d'une pièce
	 * @param p La pièce supprimé
	 */
	public void firePieceRemoved(AbstractPiece p) {
		for(IPlateauListener l : m_listeners) {
			l.pieceRemoved(p);
		}
	}

	/**
	 * Notifie le déplacement d'une pièce
	 * @param p La pièce déplacée
	 */
	public void firePieceMoved(AbstractPiece p) {
		for(IPlateauListener l : m_listeners) {
			l.pieceMoved(p);
		}
	}

	/**
	 * Notifie la rotation de la pièce
	 * @param p La pièce qui a fait une rotation
	 */
	public void firePieceRotated(AbstractPiece p) {
		for(IPlateauListener l : m_listeners) {
			l.pieceRotated(p);
		}
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		for(int y = 0 ; y < m_height ; y++) {
			for(int x = 0 ; x < m_width ; x++) {
				char c = '.';
				for(AbstractPiece p : m_pieces) {
					if(p.isCaseFilledAt(x - p.getPosition().getX(), y - p.getPosition().getY())) {
						c = 'X';
						break;
					}
					
				}
				buffer.append(c);
			}
			
			buffer.append("\n");
		}
		
		return buffer.toString();
	}

	public List<AbstractPiece> getPieces() {
		return m_pieces;
	}

	public void setPieces(List<AbstractPiece> pieces) {
		this.m_pieces = pieces;
	}

	@Override
	public int getWidth() {
		return m_width;
	}

	@Override
	public int getHeight() {
		return m_height;
	}

}
